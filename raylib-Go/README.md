# Setup Go/raylib

Setup to TDD a simple [immediate mode GUI](https://en.wikipedia.org/wiki/Immediate_Mode_GUI).

This is a Go project. Run `go test ./...` to run your tests.

## Go Setup

* Download [Go](https://www.golang.org) (version 1.13 or later) and install it on your machine
* Navigate to this directory on terminal
* Run `go mod download` to download dependencies
  * This may take some time and needs [CGO](https://blog.golang.org/c-go-cgo) support (i.e. an installed C compiler) to compile some dependencies.
* Run `go test ./...` to run all tests. This verifies installation.

## Folder Structure

The folder structure follows the guidelines of https://github.com/golang-standards/project-layout .

* `cmd/` contains dedicated applications that can be run by entering their directory and run `go run .`.
* `internal/` contains the packages of this exercise. They can not be imported from other projects and are only visible to packages from this project.

## Libraries and Utilities

The UI framework is a [Go wrapper](https://github.com/gen2brain/raylib-go) of [raylib](http://www.raylib.com/).

* `cmd/ui-framework-poc/` contains the raylib example to verify it can be built and run.
* Use the [cheatsheet](https://www.raylib.com/cheatsheet/cheatsheet.html) for an overview of functions.

The used linter is [golangci-lint](https://github.com/golangci/golangci-lint) ([v1.21.0](https://github.com/golangci/golangci-lint/releases/tag/v1.21.0))

* Run `golangci-lint run ./...` to perform analysis.
