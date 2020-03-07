import { Controller, Get,Post, Middleware } from '@overnightjs/core';
import { NextFunction,Request,Response } from 'express';
const passport = require ('passport')
const googleStrategy = require('./../middleware/googleStrategy')
@Controller('login')
export class LoginController {
    @Get('')
    get(req:Request,res:Response,next:NextFunction){
        console.log("Resposta de get",{
            req:req,
            res:res,
        })
    }

    @Get('google')
    @Middleware(passport.authenticate('google',{scope:'email'}))
    getGoogle(req: Request, res: Response, next: NextFunction): any {
        console.log("Resposta de getGoogle",{
            req:req,
            res:res,
        })
    }

    @Post('local:username:password')
    post(req:Request,res:Response,next:NextFunction):any{
        console.log("Resposta de post",{
            req:req,
            res:res,
        })
    }
}