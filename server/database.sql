SET CHARACTER SET utf8;

CREATE DATABASE IF NOT EXISTS m2;

USE m2;

CREATE TABLE IF NOT EXISTS menu_items (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  price VARCHAR(255) NOT NULL,
  has_gluten TINYINT(1) NOT NULL,
  calories INT NOT NULL,
  image VARCHAR(255) NOT NULL
);

DELETE FROM menu_items;

INSERT INTO menu_items(name, description, price, has_gluten, calories, image)
  VALUES 
    ('Frango Assado', 'Frango assado temperado', '50', 0, 100, 'http://www.irismassas.com.br/wp-content/uploads/2015/06/frango-assado.png'),
    ('Picanha', 'Picanha mal passada', '80', 0, 250, 'https://folhadomate.com/wp-content/uploads/2020/08/mini_DYrtss4X0AAycKe.jpg'),
    ('Filet Mignon', 'Passe fome com estilo', '120', 0, 150, 'https://tatyanaseverydayfood.com/wp-content/uploads/2014/04/Easy-Filet-Mignon-2.jpg'),
    ('Batata Frita', 'Crocante', '20', 1, 150, 'https://static.clubedaanamariabraga.com.br/wp-content/uploads/2019/02/batata-frita-sequinha-crocante.jpeg'),
    ('Purê de batata', 'Caseiro', '10', 1, 50, 'https://t2.rg.ltmcdn.com/pt/images/3/5/6/pure_de_batata_com_couve_flor_8653_600.jpg'),
    ('Arroz', 'Para acompanhar', '10', 1, 50, 'https://d1uz88p17r663j.cloudfront.net/resized/508e90cc752880608500ad1646fd510e_ARROZ-BASICO-RECEITAS-NESTLE_1200_600.jpg'),
    ('Sushi', 'Peixe cru', '50', 0, 50, 'https://www.rbsdirect.com.br/imagesrc/30489723.jpg?w=700'),
    ('Bife do vazio', 'Com tempero especial', '50', 0, 100, 'https://img.itdg.com.br/tdg/images/blog/uploads/2017/12/bife-do-vazio.jpg?w=1200');




    