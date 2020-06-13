var passport = require('passport')
var GoogleStrategy = require('passport-google-oauth').OAuth2Strategy

import { TokenJwt } from "../jwtToken/TokenGen";
import MySQL from '../mysql/mysql'

require('../config/environment')
// Use the GoogleStrategy within Passport.
//   Strategies in Passport require a `verify` function, which accept
//   credentials (in this case, an accessToken, refreshToken, and Google
//   profile), and invoke a callback with a user object.
const GOOGLE_AUTHORIZATION_URL = "https://accounts.google.com/o/oauth2/v2/auth"
const GOOGLE_TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token"
const GOOGLE_USERINFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo"

const mysql = MySQL.getInstance();


passport.use(new GoogleStrategy({
  clientID: process.env.GOOGLE_CLIENT_ID,
  clientSecret: process.env.GOOGLE_CLIENT_SECRET,
  callbackURL: "/login/google/callback"
},
  function (accessToken: any, refreshToken: any, profile: any, done: any) {

    const userEmail = profile.emails[0].value

    mysql.executeQuery('SELECT * FROM user ',(usuaris:any)=>{
      
      usuaris.forEach((usuari:any) => {
        if (usuari.email == userEmail){
          let jwtToken = TokenJwt.generateToken(userEmail)
          return done(null, jwtToken)  
        }
      });

      done(null,'Este correo no está en nuesta base de datos')
    })

  }


))