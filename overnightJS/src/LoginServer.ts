/*Enviroment Variables */
require('./config/environment')

import * as bodyParser from 'body-parser';
import { Server } from '@overnightjs/core';
import { Logger } from '@overnightjs/logger';
import * as cors from 'cors'
import * as passport from 'passport'
import { LoginController } from './controller/LoginController';

export class LoginServer extends Server {
    constructor() {
        super(process.env.NODE_ENV === 'development');
        this.app.use(bodyParser.json())
        this.app.use(bodyParser.urlencoded({ extended: true }))

        //CORS
        let whitelistString: string = <string>process.env.WHITELIST
        const whitelist = whitelistString.split(',')
        const options: cors.CorsOptions = {
            origin:whitelist,
            credentials: true,
            allowedHeaders: 'Authorization, Origin, X-Requested-With, Content-Type, Accept',
            methods: 'POST, GET, DELETE, PUT, OPTIONS',
            maxAge: 3600
        }
        this.app.use(cors(options))
        this.app.use(passport.initialize())
        this.setupControllers()

    }

    private setupControllers(): void {
        const loginController = new LoginController()
        super.addControllers([loginController])
    }

    public start(port: number): void {
        this.app.listen(port, () => {
            Logger.Imp('Server listening on port: ' + port);
        })
    }
}
