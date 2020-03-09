import mysql = require('mysql');
export default class MySQL {
    private static _instance;
    cnn: mysql.Connection;
    conectado: boolean;
    constructor();
    static get instance(): MySQL;
    static ejecutarQuery(query: string, callback: Function): void;
    private conectarDB;
}
