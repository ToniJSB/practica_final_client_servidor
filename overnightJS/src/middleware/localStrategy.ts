import { TokenJwt } from "../jwtToken/TokenGen";
import MySQL from "../mysql/mysql";

const mysql = MySQL.getInstance();
var passport = require('passport')
var LocalStrategy = require('passport-local').Strategy;

  
passport.use(new LocalStrategy({
  usernameField: 'email'
},

  async function(email:any, password:any, done:any) {
    
    mysql.executeQuery('SELECT * FROM user where user.email = "'+email+'"',(usuari:any)=> {
      
      console.log(usuari)

      if (usuari[0].password == password){
        console.log(usuari[0].email) 
        return done(null,usuari[0].email)
      }else{
        console.log('error')
          return done('error de auth')
      }


    
    })
    /*  User.findOne({ username: username }, function(err:any, user:any) {
      if (err) { return done(err); }
      if (!user) {
        return done(null, false, { message: 'Incorrect username.' });
      }
      if (!user.validPassword(password)) {
        return done(null, false, { message: 'Incorrect password.' });
      }
      return done(null, user);
    }); */
  }
));