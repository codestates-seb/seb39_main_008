import { rest } from 'msw';

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
    const headers = convertHeaders(req.headers);
    console.log(headers.msg);

    return res(
      ctx.json({
        msg: 'ok',
      })
    );
  }),

  rest.get('/api/v1/users/:userId', (req, res, ctx) => {
    const { userId } = req.params;

    return res(
      ctx.json({
        id: userId,
        firstName: 'John',
        lastName: 'Maverick',
      })
    );
  }),
];
