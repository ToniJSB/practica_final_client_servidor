import * as bodyParser from 'body-parser'

import { Server } from '@overnightjs/core'
import { LoginController } from './controller/loginController'

export class LoginServer extends Server {
    constructor() {
        super()
        this.app.use(bodyParser.json())
        this.app.use(bodyParser.urlencoded({ extended: true }));

        let loginController = new LoginController();
        super.addControllers([loginController])
    }

    public start(port: 3000) {
        this.app.listen(port, () => {
            console.log('Server listening on port: ' + port);
        })
    }
}