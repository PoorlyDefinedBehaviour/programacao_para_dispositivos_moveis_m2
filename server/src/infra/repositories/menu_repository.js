const db = require("../database")

const getMenu = () => {
  const sql= `
  SELECT 
    id,
    name,
    description,
    price,
    has_gluten,
    calories,
    image  
  FROM
    menu_items;
  `;

  return db.query(sql)
}

module.exports = {
  getMenu
}