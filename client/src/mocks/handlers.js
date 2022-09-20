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
    const filterby = reqUrl.get('filterby') || 'recentdesc';
    let filterdData = [];

    switch (filterby) {
      case 'recentdesc':
        filterdData = memberData.sort(
          (a, b) =>
            new Date(b.created_at).getTime() - new Date(a.created_at).getTime()
        );
        break;
      case 'recentasc':
        filterdData = memberData.sort(
          (a, b) =>
            new Date(a.created_at).getTime() - new Date(b.created_at).getTime()
        );
        break;
      case 'followdesc':
        filterdData = memberData.sort((a, b) => b.follower - a.follower);
        break;
      case 'followasc':
        filterdData = memberData.sort((a, b) => a.follower - b.follower);
        break;
      case 'totalwritedesc':
        filterdData = memberData.sort(
          (a, b) => b.total_content - a.total_content
        );
        break;
      case 'totalwriteasc':
        filterdData = memberData.sort(
          (a, b) => a.total_content - b.total_content
        );
        break;
      default:
        filterdData = memberData.sort(
          (a, b) =>
            new Date(b.created_at).getTime() - new Date(a.created_at).getTime()
        );
        break;
    }

    const resData =
      page <= 1
        ? filterdData.slice(0, size)
        : filterdData.slice((page - 1) * size, (page - 1) * size + size);

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

    return res(
      ctx.json({
        data: memberData.filter((e) => e.id === Number(memberId)),
        ok: true,
      })
    );
  }),

  rest.get('/api/v1/books/:book_id', (req, res, ctx) => {
    const { book_id } = req.params;
    let filterdData = diarylist.sort(
      (a, b) =>
        new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
    );

    return res(
      ctx.json({
        data: filterdData.filter((e) => Number(book_id) === e.bookId),
        ok: true,
      })
    );
  }),

  rest.get('/api/v1/books', (req, res, ctx) => {
    const reqUrl = req.url.searchParams;
    const memberid = reqUrl.get('memberid');
    let filterdData = booklist.sort(
      (a, b) =>
        new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
    );

    return res(
      ctx.json({
        data: filterdData.filter((e) => Number(memberid) === e.memberid),
        ok: true,
      })
    );
  }),

  rest.get('/api/v1/diaries', (req, res, ctx) => {
    const reqUrl = req.url.searchParams;
    const page = Number(reqUrl.get('page')) || 1;
    const size = Number(reqUrl.get('size')) || 10;
    const filterby = reqUrl.get('filterby') || 'recentdesc';
    let filterdData = [];

    switch (filterby) {
      case 'recentdesc':
        filterdData = diarylist.sort(
          (a, b) =>
            new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
        );
        break;
      case 'recentasc':
        filterdData = diarylist.sort(
          (a, b) =>
            new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
        );
        break;
      case 'likedesc':
        filterdData = diarylist.sort((a, b) => b.totalLike - a.totalLike);
        break;
      case 'likeasc':
        filterdData = diarylist.sort((a, b) => a.totalLike - b.totalLike);
        break;
      case 'commentdesc':
        filterdData = diarylist.sort((a, b) => b.totalComment - a.totalComment);
        break;
      case 'commentasc':
        filterdData = diarylist.sort((a, b) => a.totalComment - b.totalComment);
        break;
      default:
        filterdData = diarylist.sort(
          (a, b) =>
            new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime()
        );
        break;
    }

    filterdData = filterdData.map((e) => {
      delete e.bookId;
      const result = {
        ...memberData.filter((el) => e.memberId === el.id)[0],
        ...e,
      };
      delete result.id;

      return result;
    });

    const resData =
      page <= 1
        ? filterdData.slice(0, size)
        : filterdData.slice((page - 1) * size, (page - 1) * size + size);

    return res(
      ctx.json({
        data: resData,
        ok: true,
      })
    );
  }),

  rest.get('/api/v1/diaries/:diaries_id', (req, res, ctx) => {
    const { diaries_id } = req.params;
    let filterdData = diarylist.filter((e) => Number(diaries_id) === e.diaryId);

    filterdData = filterdData.map((e) => {
      delete e.bookId;
      const result = {
        ...memberData.filter((el) => e.memberId === el.id)[0],
        ...e,
      };
      delete result.id;

      return result;
    });

    return res(
      ctx.json({
        data: { ...filterdData[0] },
        ok: true,
      })
    );
  }),
];
