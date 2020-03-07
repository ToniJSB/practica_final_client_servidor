import { Controller, Get, Middleware } from '@overnightjs/core';
import { NextFunction,Request,Response } from 'express';
const passport = require ('passport')

@Controller('login')
export class LoginController {
    @Get('')
    @Middleware(passport.authenticate('google'))
    get(req: Request, res: Response, next: NextFunction): any {
        console.log(req)
    }
}