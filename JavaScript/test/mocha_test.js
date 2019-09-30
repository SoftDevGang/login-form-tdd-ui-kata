"use strict";

var chai = require("chai");
chai.should();
var expect = require("chai").expect;

describe("Mocha infrastructure", function() {

    it("should assert with Chai", function() {
        (1 + 1).should.equal(2);
    });

    it("should expect with Chai", function() {
        expect(1 + 1).to.equal(2);
    });

});
