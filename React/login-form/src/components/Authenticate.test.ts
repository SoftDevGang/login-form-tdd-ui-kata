import { rest } from 'msw'
import { setupServer } from 'msw/node';
import { authenticate, AuthenticateResult } from "./Authenticate";

let statusCode = 200;
let body = { success: false, message: "" };

const server = setupServer(
    rest.post('/api/v1/login', (req, res, ctx) => {
        return res(
            ctx.status(statusCode),
            ctx.set('Content-Type', 'application/json'),
            ctx.json(body)
        );
    }),
);

afterEach(() => server.resetHandlers());
beforeAll(() => server.listen());
afterAll(() => server.close());

// 3. drive backend service with integrated web server
test('Authenticate calls backend endpoint', async () => {
    statusCode = 200;
    body = { success: true, message: "I was called" };

    const actual: AuthenticateResult = await authenticate("Bob", "Bobs password");

    expect(actual.success).toBeTruthy();
    expect(actual.message).toBe("I was called");
});

// 3.1 check for proper POST request containing username and password

// 3.2 add login failures and error situations
test('Server denies access', async () => {
    statusCode = 200;
    body = { success: false, message: "" };

    const actual: AuthenticateResult = await authenticate("Bob", "Bobs password");

    expect(actual.success).toBeFalsy();
});

test('Server errors deny access', async () => {
    statusCode = 404;
    body = { success: true, message: "" };

    const actual: AuthenticateResult = await authenticate("Bob", "Bobs password");

    expect(actual.success).toBeFalsy();
});
