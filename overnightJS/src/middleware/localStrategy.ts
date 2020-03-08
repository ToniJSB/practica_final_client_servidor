import MySQL from "../mysql/mysql";

var passport = require('passport')
  , LocalStrategy = require('passport-local').Strategy;

  var email  = '551'
  
passport.use(new LocalStrategy(

  function(email:any, password:any, done:any) {
    console.log('entra')
    const query = 'SELECT * FROM user WHERE email = ' + email

    MySQL.ejecutarQuery(query, function (err: any, result: any, done: any) {

      console.log(result)
    
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