import { Controller, Get, Post, Middleware } from '@overnightjs/core';
import { NextFunction, Request, Response } from 'express';

const googleStrategy = require('../middleware/googleStrategy');
const localStrategy = require('../middleware/localStrategy');
const passport = require('passport');

import { TokenJwt } from "../jwtToken/TokenGen";

import * as jwt from 'jsonwebtoken';

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

        res.redirect("http://localhost:8081/#/private/posts?token="+req.user)
    }

    @Post('local')
//    @Middleware(passport.authenticate('local',{session: false} ))
    loginLocal(req: Request, res: Response, next: NextFunction): any {
        passport.authenticate('local',{session: false}, (err:any,user:any,info:any)=>{
            console.log(user+' _user')
            console.log(err+' _error')
            console.log(info+' _info')

            req.login(user, {session: false}, (err)=>{
                //Construir el jwt

                const token = TokenJwt.generateToken(user);
                console.log(token)

                return res.json(token)

//                res.redirect("http://localhost:8081/#/private/posts?token="+token)
            })

        } )(req,res)
    }


}