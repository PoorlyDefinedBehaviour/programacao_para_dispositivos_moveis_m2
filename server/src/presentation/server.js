const express = require('express');
const menuController = require("../controllers/menu_controller")

const app = express();

app.get('/menu', menuController.getMenu)

app.listen(3333, () => console.log("Server on"));
 