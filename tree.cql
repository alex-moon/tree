MATCH (n) DETACH DELETE n;

CREATE
    // BRANCHES
    (maxi:Branch {id: 1, postcode:'N4 1LS', name:'Maxi Branch'}),
    (mini:Branch {id: 2, postcode:'NE3 1AT', name:'Mini Branch'}),

    // PRODUCTS
    (beer:Product {name:'Beer', barcode:'123456789'}),
    (wine:Product {name:'Wine', barcode:'987654321'}),
    (vodka:Product {name:'Vodka', barcode:'11223344'}),

    // CUSTOMERS
    (alex:Customer {id: 1, name: 'Alex Moon'}),
    (joe:Customer {id: 2, name: 'Joe Faquechitte'}),

    // TRANSACTIONS
    (alex_maxi_beer:Transaction {reference: 'REF12345'}),
    (alex_mini_wine:Transaction {reference: 'REF23456'}),
    (alex_maxi_vodka:Transaction {reference: 'REF34567'}),
    (joe_mini_beer:Transaction {reference: 'REF45678'}),
    (joe_maxi_wine:Transaction {reference: 'REF56789'}),
    (joe_mini_gin:Transaction {reference: 'REF67890'}),

    // now connect it all together lol
    (alex)-[:TRANSACTED]->(alex_maxi_beer)<-[:TRANSACTED]-(maxi),
    (beer)-[:TRANSACTED]->(alex_maxi_beer),
    (alex)-[:TRANSACTED]->(alex_mini_wine)<-[:TRANSACTED]-(mini),
    (wine)-[:TRANSACTED]->(alex_mini_wine),
    (alex)-[:TRANSACTED]->(alex_maxi_vodka)<-[:TRANSACTED]-(maxi),
    (vodka)-[:TRANSACTED]->(alex_maxi_vodka),
    (joe)-[:TRANSACTED]->(joe_mini_beer)<-[:TRANSACTED]-(mini),
    (beer)-[:TRANSACTED]->(joe_mini_beer),
    (joe)-[:TRANSACTED]->(joe_maxi_wine)<-[:TRANSACTED]-(maxi),
    (wine)-[:TRANSACTED]->(joe_maxi_wine),
    (joe)-[:TRANSACTED]->(joe_mini_gin)<-[:TRANSACTED]-(mini),
    (gin)-[:TRANSACTED]->(joe_mini_gin)
;
