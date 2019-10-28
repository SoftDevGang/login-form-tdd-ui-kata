package login

type FormUI interface {
	Button() bool
}

// Form renders the controls for providing the necessary login credentials.
type Form struct{}

func (form Form) Render(ui FormUI) {
	ui.Button()
}
