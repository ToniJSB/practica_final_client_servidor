import { NextFunction, Request, Response } from 'express';
export declare class LoginController {
    get(req: Request, res: Response, next: NextFunction): void;
    loginGoogle(): any;
    loginGoogleCallback(req: Request, res: Response, next: NextFunction): any;
    loginLocal(req: Request, res: Response, next: NextFunction): any;
    private generateToken;
}
