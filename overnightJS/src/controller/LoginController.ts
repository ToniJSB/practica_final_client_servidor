import { Controller, Get, Post, Middleware } from '@overnightjs/core';
import { NextFunction, Request, Response } from 'express';
const passport = require('passport')
const googleStrategy = require('./../middleware/googleStrategy')
@Controller('login')
export class LoginController {

    @Get('')
    get(req: Request, res: Response, next: NextFunction) {
        console.log("Resposta de get", {
            req: req,
            res: res,
        })
    }

    @Get('google')
    @Middleware(passport.authenticate('google', { scope: 'email' }))
    loginGoogle(): any {}

    @Get('google/callback')
    @Middleware(passport.authenticate('google', { session: false }))
    loginGoogleCallback(req: Request, res: Response, next: NextFunction): any {
        console.log("autentificacio amb Google CALLLBACK", {
            req: req,
            res: res,
        })
    }

    @Get('local')
    loginLocal(req: Request, res: Response, next: NextFunction): any {
        console.log("autentificacio amb local", {
            req: req,
            res: res,
        })
    }

    //Local
}