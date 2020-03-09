"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var tslib_1 = require("tslib");
var core_1 = require("@overnightjs/core");
var googleStrategy = require('./../middleware/googleStrategy');
var localStrategy = require('./../middleware/localStrategy');
var jwt = require('jsonwebtoken');
var passport = require('passport');
require('../config/environment');
var LoginController = (function () {
    function LoginController() {
    }
    LoginController.prototype.get = function (req, res, next) {
    };
    LoginController.prototype.loginGoogle = function () { };
    LoginController.prototype.loginGoogleCallback = function (req, res, next) {
        res.send(this.generateToken(req.user));
    };
    LoginController.prototype.loginLocal = function (req, res, next) {
        console.log("autentificacio amb local", {
            req: req,
            res: res,
        });
    };
    LoginController.prototype.generateToken = function (email) {
        var tokenData = {
            sub: email
        };
        var token = jwt.sign(tokenData, process.env.TOKEN_SECRET, {
            expiresIn: 60 * 60 * 24
        });
        return token;
    };
    tslib_1.__decorate([
        core_1.Get(''),
        tslib_1.__metadata("design:type", Function),
        tslib_1.__metadata("design:paramtypes", [Object, Object, Function]),
        tslib_1.__metadata("design:returntype", void 0)
    ], LoginController.prototype, "get", null);
    tslib_1.__decorate([
        core_1.Get('google'),
        core_1.Middleware(passport.authenticate('google', { scope: 'email' })),
        tslib_1.__metadata("design:type", Function),
        tslib_1.__metadata("design:paramtypes", []),
        tslib_1.__metadata("design:returntype", Object)
    ], LoginController.prototype, "loginGoogle", null);
    tslib_1.__decorate([
        core_1.Get('google/callback'),
        core_1.Middleware(passport.authenticate('google', { session: false })),
        tslib_1.__metadata("design:type", Function),
        tslib_1.__metadata("design:paramtypes", [Object, Object, Function]),
        tslib_1.__metadata("design:returntype", Object)
    ], LoginController.prototype, "loginGoogleCallback", null);
    tslib_1.__decorate([
        core_1.Post('local'),
        core_1.Middleware(passport.authenticate('local', { session: false })),
        tslib_1.__metadata("design:type", Function),
        tslib_1.__metadata("design:paramtypes", [Object, Object, Function]),
        tslib_1.__metadata("design:returntype", Object)
    ], LoginController.prototype, "loginLocal", null);
    LoginController = tslib_1.__decorate([
        core_1.Controller('login')
    ], LoginController);
    return LoginController;
}());
exports.LoginController = LoginController;
//# sourceMappingURL=LoginController.js.map