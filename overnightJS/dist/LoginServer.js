"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var tslib_1 = require("tslib");
require('./config/environment');
var bodyParser = require("body-parser");
var core_1 = require("@overnightjs/core");
var logger_1 = require("@overnightjs/logger");
var cors = require("cors");
var passport = require("passport");
var LoginController_1 = require("./controller/LoginController");
var LoginServer = (function (_super) {
    tslib_1.__extends(LoginServer, _super);
    function LoginServer() {
        var _this = _super.call(this, process.env.NODE_ENV === 'development') || this;
        _this.app.use(bodyParser.json());
        _this.app.use(bodyParser.urlencoded({ extended: true }));
        var whitelistString = process.env.WHITELIST;
        var whitelist = whitelistString.split(',');
        var options = {
            origin: whitelist,
            credentials: true,
            allowedHeaders: 'Authorization, Origin, X-Requested-With, Content-Type, Accept',
            methods: 'POST, GET, DELETE, PUT, OPTIONS',
            maxAge: 3600
        };
        _this.app.use(cors(options));
        _this.app.use(passport.initialize());
        _this.setupControllers();
        return _this;
    }
    LoginServer.prototype.setupControllers = function () {
        var loginController = new LoginController_1.LoginController();
        _super.prototype.addControllers.call(this, [loginController]);
    };
    LoginServer.prototype.start = function (port) {
        this.app.listen(port, function () {
            logger_1.Logger.Imp('Server listening on port: ' + port);
        });
    };
    return LoginServer;
}(core_1.Server));
exports.LoginServer = LoginServer;
//# sourceMappingURL=LoginServer.js.map