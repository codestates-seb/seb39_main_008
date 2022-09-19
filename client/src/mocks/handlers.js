import { rest } from 'msw';
export const handlers = [
  // Handles a POST /login request
  rest.post('/login', null),
  // Handles a GET /user request
  rest.get('/users/:userId', (req, res, ctx) => {
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
