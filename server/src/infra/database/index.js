const express = require('express');
const mysql = require('mysql');
const {promisify} = require("util")

const connection = mysql.createConnection({
  host:  "localhost",
  user: "root",
  password: "password",
  database: "m2"
});

connection.connect((err) => {
  if (err){
    throw err;
  }
  console.log("Connection with mysql established");
});

const query = promisify(connection.query.bind(connection))

module.exports = {
  query
}