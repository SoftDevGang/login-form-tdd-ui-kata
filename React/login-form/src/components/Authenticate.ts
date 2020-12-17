export interface AuthenticateResult {
    success: boolean;
    message?: string;
}

export async function authenticate(userName: string, password: string): Promise<AuthenticateResult> {
    const payload = {
        userName: userName,
        password: password
    };

    return fetch('http://localhost/api/v1/login', {
        method: "POST",
        body: JSON.stringify(payload)
    }).then(res => res.json());
}
