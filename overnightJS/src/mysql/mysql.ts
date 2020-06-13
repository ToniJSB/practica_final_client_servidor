import mysql = require('mysql');
require('../config/environment')

export default class MySQL {

    private static instance: MySQL;

    cnn: mysql.Connection;

    conectado: boolean = false;

    private constructor() {

        console.log('Clase inicializada');

        this.cnn = mysql.createConnection({
            host: 'localhost',
            user: process.env.DB_USER,
            password: process.env.DB_PASSWORD,
            database: process.env.DB_NAME
        });

        this.conectDB();
    }

    public static getInstance() {
        if (this.instance==null){
            this.instance = new this()
        }
        return this.instance;
    }

    public executeQuery(query: string, callback: Function) {

        this.cnn.query(query, (err, results: Object[], fields) => {

            if (err) {
                console.log('Error en la query');
                console.log(err);
                return callback(err);
            }

            if (results.length === 0) {
                return callback('El registro solicitado no existe');
            } else {
                return callback(results);
            }

        });
    }


    private conectDB() {
        this.cnn.connect((err: mysql.MysqlError) => {

            if (err) {
                console.log(err.message);
                return;
            }

            this.conectado = true;
            console.log('DB Online');

        })
    }

} 