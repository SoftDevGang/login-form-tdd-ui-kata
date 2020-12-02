# Login Form TDD React

Remote pair-programming code kata by [Thomas Sundberg](https://www.thinkcode.se/)
and [Peter Kofler](https://www.code-cop.org/): working on
[Login Form TDD a UI Kata](https://blog.code-cop.org/2020/01/login-form-tdd-ui-kata.html).

## Goal

- Create sample code for a blog post showing TDD on the UI using React. 50%
- See how it is done in React. 50%
- Is it good enough/fast enough not to separate the UI and the model? Yes.
- Is it unit test? Not an important question. No. Feels like Micro test. Yes.

## Sessions

### 23.11.2020

- 4 pomodoros
- 11:00 getting into it
- 11:30 start with small steps
- 12:45 "big step in small steps"
- 13:45 retro

#### Learnings

- Peter: react component allows refactor/pressure in the known sense
  maybe also true for any UI but never thought about it
- visible ui widgets are tested implicitly, not tested it
- React has some UI model, so we need to its test connection.
  But it is not recommended to look into data from outside.

#### Retro

- Thomas says we are taking smaller steps than he thought.
- Easy to make assumptions alone, other person has different assumptions.
- takes a lot of time for details.
- small steps take more time?
- want feedback sooner

#### TODO for next

- (Peter) use Authentication.ts in real when Authentication code is finished.
- Show error message at login failure
- Show next component at login success

### 02.12.2020

- x pomodoros
- 10:00 get back into code
- 10:30 add second field to look complete
- 11:00 failed login
- 12:00

#### Tests

- no test for field type
- no test for field id.
- no test for displaying ~~and at beginning setting state of~~ field

#### Learnings

- We recycled tests/added fields to them incrementally. Why not create a new test? Too much setup?
- how technical should UI tests be? "be type password" or "display asterisks"?
- could have our own password field which can display clear text on check box
- sketch the UI logic to know how to test for it?
- Add tests for state/display we do not want?
- Factory message for sut helps, as always. Duplication!
- Tests are OK but App does not compile due missing required attribute? wtf

#### TODO for next

- Refactor App to class.
- Service Worker setup
- Success case which shows "Welcome Message"
