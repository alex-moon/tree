MATCH (n)
DETACH DELETE n
;

CREATE (maxi:Branch {id:'1', postcode:'N4 1LS', name:'Maxi Branch'})
CREATE (mini:Branch {id:'2', postcode:'NE3 1AT', name:'Mini Branch'})
CREATE (beer:Product {name:'Beer', barcode:'123456789'})
CREATE (wine:Product {name:'Wine', barcode:'987654321'})
CREATE (vodka:Product {name:'Vodka', barcode:'11223344'})
CREATE (maxi)-[:STOCKS]->(beer)
CREATE (maxi)-[:STOCKS]->(wine)
CREATE (mini)-[:STOCKS]->(beer)
CREATE (mini)-[:STOCKS]->(vodka)
;
