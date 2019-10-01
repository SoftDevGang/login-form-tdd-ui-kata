"use strict";

// see https://gist.github.com/robballou/9ee108758dc5e0e2d028
var { JSDOM } = require("jsdom");

var chai = require("chai");
chai.use(require("chai-dom"));
var expect = chai.expect;

var window;
/** @type {HTMLDocument} */
var document;

describe("JSDom infrastructure", function() {

    beforeEach(function() {
        var jsdom = new JSDOM("<!doctype html><html><body><h1>Hello</h1></body></html>");
        window = jsdom.window;
        document = window.document;
    });

    it("should assert header", function() {
        expect(document.getElementsByTagName("h1").length).to.equal(1);
    });

    it("should assert with chai-dom", function() {
        // see https://www.chaijs.com/plugins/chai-dom/
        expect(document.getElementsByTagName("h1").item(0)).text("Hello");
    });

});
