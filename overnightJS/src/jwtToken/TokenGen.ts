const jwt = require('jsonwebtoken');



export class TokenJwt {

    static generateToken(accessToken: any) {
        const tokenData = {
            atk: accessToken
        }
        let token = jwt.sign(tokenData , process.env.TOKEN_SECRET)
        return token;
    }

}