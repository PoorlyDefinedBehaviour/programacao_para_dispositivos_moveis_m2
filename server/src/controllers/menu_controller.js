const menuRepository = require("../infra/repositories/menu_repository")
const X2JS = require('x2js');

const xml = new X2JS()

const getMenu = async (req, res) =>{
  const items = await menuRepository.getMenu()

  const contentType = req.header("content-type")

  console.log(`${contentType} request`)

  if(contentType === "application/json") {
    return res.status(200).type(contentType).send({ items })
  } else if(contentType === "application/xml") {
    const payload = {
      items: {
        item: items
      }
    }
    return res.status(200).type(contentType).send('<?xml version = "1.0"?>\n' + xml.js2xml(payload))
  }
}

module.exports = {
  getMenu
}