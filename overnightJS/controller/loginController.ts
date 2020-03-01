import { Request, Response, NextFunction } from 'express'
import { Controller, Middleware, Get, Post } from '@overnightjs/core'
import { Passport } from 'passport'

@Controller('/')
export class LoginController {

    @Get('')
    @Middleware(Passport.authenticate('google'))
    get(req:Request,res:Response,next:NextFunction){
      console.log("Hola")
    }
}