package login

// Authenticator validates a name and passphrase.
// If the credentials can't be found, an error is returned.
type Authenticator interface {
	Authenticate(name, pass string) error
}
