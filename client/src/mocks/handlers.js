import { rest } from 'msw';
import { memberData, booklist, diarylist } from './dummyData';

const convertHeaders = (data) => {
  const headers = Array.from(data);
  const result = headers.reduce((acc, cur) => {
    acc[cur[0]] = cur[1];
    return acc;
  }, {});
  return result;
};

export const handlers = [
  rest.get('/api/v1/members', (req, res, ctx) => {
    const reqUrl = req.url.searchParams;
    const page = Number(reqUrl.get('page')) || 1;
    const size = Number(reqUrl.get('size')) || 10;
    const sortby = reqUrl.get('sortby') || 'recentdesc';
    let temp = memberData.map((e) =>
      diarylist.filter((el) => el.member_id === e.id)
    );
    let filteredData = memberData.reduce((acc, cur, i) => {
      cur.total_content = temp[i].length;
      acc = [...acc, cur];
      return acc;
    }, []);

    switch (sortby) {
      case 'recentdesc':
        filteredData = filteredData.sort(
          (a, b) =>
            new Date(b.created_at).getTime() - new Date(a.created_at).getTime()
        );
        break;
      case 'recentasc':
        filteredData = filteredData.sort(
          (a, b) =>
            new Date(a.created_at).getTime() - new Date(b.created_at).getTime()
        );
        break;
      case 'followdesc':
        filteredData = filteredData.sort(
          (a, b) => b.total_follower - a.total_follower
        );
        break;
      case 'followasc':
        filteredData = filteredData.sort(
          (a, b) => a.total_follower - b.total_follower
        );
        break;
      case 'totalwritedesc':
        filteredData = filteredData.sort(
          (a, b) => b.total_content - a.total_content
        );
        break;
      case 'totalwriteasc':
        filteredData = filteredData.sort(
          (a, b) => a.total_content - b.total_content
        );
        break;
      default:
        filteredData = filteredData.sort(
          (a, b) => a.total_follower - b.total_follower
        );
        break;
    }

    let resData =
      page <= 1
        ? filteredData.slice(0, size)
        : filteredData.slice((page - 1) * size, (page - 1) * size + size);

    resData = resData.map((e) => {
      return {
        memberId: e.id,
        name: e.nickname,
        profile: e.image,
        total_content: e.total_content,
        total_follower: e.total_follower,
      };
    });

    return res(
      ctx.json({
        data: resData,
        ok: true,
      })
    );
  }),

  rest.patch('/api/v1/members', (req, res, ctx) => {
    const headers = convertHeaders(req.headers);
    const authorization = headers.authorization.slice(7);
    let resData = {};

    if (authorization === 'test') {
      resData['msg'] = 'success';
      resData['ok'] = true;
    } else {
      resData['msg'] = 'permission denied';
      resData['ok'] = false;
    }

    return res(ctx.json(resData));
  }),

  rest.get('/api/v1/members/:memberId', (req, res, ctx) => {
    const { memberId } = req.params;
    const filteredData = memberData.filter((e) => e.id === Number(memberId));
    const resData = filteredData.map((e) => {
      return {
        memberId: e.id,
        name: e.name,
        nickname: e.nickname,
        imfomation: e.nickname,
        profile: e.image,
        isFollow: e.isFollow,
      };
    });

    return res(
      ctx.json({
        data: resData[0],
        ok: true,
      })
    );
  }),

  rest.get('/api/v1/books/:book_id', (req, res, ctx) => {
    const { book_id } = req.params;
    let filteredData = diarylist.filter((e) => e.book_id === Number(book_id));
    let filterdMemberData = filteredData.map((e) => e.member_id);
    filterdMemberData = memberData.filter((e) =>
      filterdMemberData.includes(e.id)
    );

    const resData = filteredData.reduce((acc, cur, i) => {
      const temp = {
        diariesid: cur.id,
        memberid: cur.member_id,
        bookimage: cur.image,
        title: cur.title,
        createdAt: cur.created_at,
        nickname: filterdMemberData[i].nickname,
        profile: filterdMemberData[i].image,
      };
      return [...acc, { ...temp }];
    }, []);

    return res(
      ctx.json({
        data: resData,
        ok: true,
      })
    );
  }),

  rest.get('/api/v1/books', (req, res, ctx) => {
    const reqUrl = req.url.searchParams;
    const memberid = reqUrl.get('memberid');
    let filteredData = booklist.filter((e) => Number(memberid) === e.member_id);

    return res(
      ctx.json({
        data: filteredData,
        ok: true,
      })
    );
  }),

  rest.get('/api/v1/diaries', (req, res, ctx) => {
    const reqUrl = req.url.searchParams;
    const page = Number(reqUrl.get('page')) || 1;
    const size = Number(reqUrl.get('size')) || 10;
    const sortby = reqUrl.get('sortby') || 'recentdesc';
    let filteredData = diarylist.reduce((acc, cur) => {
      const filteredMember = memberData.filter(
        (e) => e.id === cur.member_id
      )[0];
      const temp = {
        diaryId: cur.id,
        title: cur.title,
        subtitle: cur.subtitle,
        diaryimage: cur.image,
        totalLike: cur.total_like,
        totalComment: cur.total_comment,
        createdAt: cur.created_at,
        profile: filteredMember.image,
        memberId: filteredMember.id,
        nickname: filteredMember.nickname,
        isFollow: filteredMember.isFollow,
      };
      return [...acc, { ...temp }];
    }, []);
    switch (sortby) {
      case 'recentdesc':
        filteredData = filteredData.sort(
          (a, b) =>
            new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
        );
        break;
      case 'recentasc':
        filteredData = filteredData.sort(
          (a, b) =>
            new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
        );
        break;
      case 'likedesc':
        filteredData = filteredData.sort((a, b) => b.totalLike - a.totalLike);
        break;
      case 'likeasc':
        filteredData = filteredData.sort((a, b) => a.totalLike - b.totalLike);
        break;
      case 'commentdesc':
        filteredData = filteredData.sort(
          (a, b) => b.totalComment - a.totalComment
        );
        break;
      case 'commentasc':
        filteredData = filteredData.sort(
          (a, b) => a.totalComment - b.totalComment
        );
        break;
      default:
        filteredData = filteredData.sort(
          (a, b) =>
            new Date(b.created_at).getTime() - new Date(a.created_at).getTime()
        );
        break;
    }

    const resData =
      page <= 1
        ? filteredData.slice(0, size)
        : filteredData.slice((page - 1) * size, (page - 1) * size + size);

    return res(
      ctx.json({
        data: resData,
        ok: true,
      })
    );
  }),

  rest.get('/api/v1/diaries/:diaries_id', (req, res, ctx) => {
    const { diaries_id } = req.params;
    let filteredData = diarylist.filter((e) => Number(diaries_id) === e.id);
    filteredData = filteredData.map((e) => {
      const filteredMember = memberData.filter(
        (el) => el.id === e.member_id
      )[0];
      return {
        diaryId: e.id,
        title: e.title,
        subtitle: e.subtitle,
        diaryimage: e.image,
        totalLike: e.total_like,
        totalComment: e.total_comment,
        createdAt: e.created_at,
        profile: filteredMember.image,
        memberId: filteredMember.id,
        nickname: filteredMember.nickname,
        isFollow: filteredMember.isFollow,
      };
    });

    return res(
      ctx.json({
        data: { ...filteredData[0] },
        ok: true,
      })
    );
  }),
];
