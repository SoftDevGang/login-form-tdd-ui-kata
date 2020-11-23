export interface AuthenticateResult {
    success: boolean,
    message?: string
}

/*
export async function authenticate(userName: string, password: string): Promise<AuthenticateResult> {
    return Promise.resolve({success: false, message: ""});
}
*/