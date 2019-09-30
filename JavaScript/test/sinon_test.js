"use strict";

var expect = require("chai").expect;
var sinon = require("sinon");

function Foo() {
    // Bar
}

Foo.prototype.parse = function(value) {
    return true;
};

describe("Sinon infrastructure", function() {

    // see "https://sinonjs.org/"

    it("should assert with Sinon", function() {
        // arrange
        sinon.stub(Foo.prototype, "parse").callsFake((value) => false);
        var foo = new Foo();
        // act
        foo.parse("abc");
        // assert
        expect(foo.parse.alwaysCalledWithExactly("abc")).to.equal(true);
    });

});
