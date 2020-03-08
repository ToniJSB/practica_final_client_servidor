import { Controller, Get, Post, Middleware } from '@overnightjs/core';
import { NextFunction, Request, Response } from 'express';
const jwt = require('jsonwebtoken')
const passport = require('passport')
const googleStrategy = require('./../middleware/googleStrategy')
require('../config/environment')

@Controller('login')
export class LoginController {

    @Get('')
    get(req: Request, res: Response, next: NextFunction) {
    }

    @Get('google')
    @Middleware(passport.authenticate('google', { scope: 'email' }))
    loginGoogle(): any { }

    @Get('google/callback')
    @Middleware(passport.authenticate('google', { session: false }))
    loginGoogleCallback(req: Request, res: Response, next: NextFunction): any {
        res.send(this.generateToken(req.user))
    }

    @Get('local')
    loginLocal(req: Request, res: Response, next: NextFunction): any {
        console.log("autentificacio amb local", {
            req: req,
            res: res,
        })
    }

    private generateToken(email: any) {
        let tokenData = {
            email: email
        }
        let token = jwt.sign(tokenData, process.env.TOKEN_SECRET, {
            expiresIn: 60 * 60 * 24//Expires in 24h
        })
        return token;
    }
}