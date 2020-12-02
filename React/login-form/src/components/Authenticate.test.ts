import {rest} from 'msw'
import {setupServer} from 'msw/node';
import {authenticate, AuthenticateResult} from "./Authenticate";

const server = setupServer(
    rest.post('/api/v1/login', (req, res, ctx) => {
        let body = {success: true, message: "I was called"};
        // todo add error situations and login failures with more tests
        return res(
            ctx.status(200),
            ctx.set('Content-Type', 'application/json'),
            ctx.json(body)
        );
    }),
);

afterEach(() => server.resetHandlers());
beforeAll(() => server.listen());
afterAll(() => server.close());

test('Authenticate calls backend as expected', async () => {
    const actual: AuthenticateResult = await authenticate("Bob", "Bobs password");

    expect(actual.success).toBeTruthy();
    expect(actual.message).toBe("I was called");
});