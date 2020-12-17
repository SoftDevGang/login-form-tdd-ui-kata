const serverUrl = 'http://localhost/api/v1/login';

export interface AuthenticateResult {
    success: boolean;
    message?: string;
}

export async function authenticate(userName: string, password: string): Promise<AuthenticateResult> {
    const payload = { userName, password };

    return fetch(serverUrl, {
        method: "POST",
        body: JSON.stringify(payload)
    }).
        then(
            res => {
                if (res.status === 200) {
                    return res.json();
                }
                return { success: false, message: res.statusText };
            },
            error => {
                // TODO how do we get here? Network error?
                return { success: false, message: "" + error };
            });
}
