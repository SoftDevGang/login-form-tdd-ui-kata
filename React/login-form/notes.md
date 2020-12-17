# Login Form TDD React

Remote pair-programming code kata by [Thomas Sundberg](https://www.thinkcode.se/)
and [Peter Kofler](https://www.code-cop.org/): working on
[Login Form TDD a UI Kata](https://blog.code-cop.org/2020/01/login-form-tdd-ui-kata.html).

## Goal

- (T) Create sample code for a blog post showing TDD on the UI using React. 75%
- (P) See how it is done in React. Done.
- (P) Is it good enough/fast enough not to separate the UI and the model? Yes.
- (P) Is it unit test? Not an important question. No. Feels like Micro test. Yes.

## Sessions

### 23.11.2020

- 4 pomodoros
- 11:00 getting into it
- 11:30 start with small steps
- 12:45 "big step in small steps" ||
- 13:45 retro

#### Learnings #1

- Peter: React component allows refactor/pressure in the known sense
  maybe also true for any UI but never thought about it
- visible UI widgets are tested implicitly, not tested directly
- React has some UI model, so we need to its test connection.
  But it is not recommended to look into data from outside.

#### Retrospective #1

- Thomas says we are taking smaller steps than he thought.
- Easy to make assumptions alone, other person has different assumptions.
- takes a lot of time for details.
- small steps take more time?
- want feedback sooner

#### TODO for next: interaction

- (Peter) use Authentication.ts in real when Authentication code is finished.
- Show error message at login failure
- Show next component at login success

### 02.12.2020

- 7 pomodoros
- 10:00 get back into code
- 10:30 add second field to look complete
- 11:00 failed login ||
- Thomas on lunch break Refactor App to class/Service Worker setup
- 13:00 authenticate does a web call
- 13:30 system test for app for successful login
- 14:00 retro

#### Tests

- no test for field type.
- no test for field id.
- no test for displaying ~~and at beginning setting state of~~ field

#### Learnings #2

- We recycled tests/added fields to them incrementally.
  Why not create a new test? Too much setup?
- how technical should UI tests be? "be type password" or "display asterisks"?
- could have our own password field which can display clear text on check box
- sketch the UI logic to know how to test for it?
- Add tests for state/display we do not want?
- Factory message for sut helps, as always. Duplication!
- Tests are OK but App does not compile due missing required attribute? wtf
- What about duplication between single (unit) test and app tests when invoking the same thing?
- Add UI hardcoded on red test - green - I know what result of logic should be.
- [Mock Service Worker](https://mswjs.io/)

#### Retrospective #2

- better than last time, progress ||
- less yak shaving than last time ;-)
- see the whole picture
- easy design vs. login component has no logic
- in UI parent is always orchestrating its children
- React removes the boundary between code and markup/UI - easy refactoring.
  (Only state/props annoying)
- Could split components in Login even further -> 3 components, flexibility
- dive right into it. nice
- testing library: pretty fast, works well, we test everything using the UI,
  encourages using the UI for all things.

#### TODO for next: clean up

- extract authenticate callback from render function.
- do not show Login component on success
- see the username in welcome message
- features: test failure case and pass the failure flag (not related TDD UI)
