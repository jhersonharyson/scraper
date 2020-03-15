const express = require("express");
const bodyParser = require("body-parser");
const os = require('os')
const cors = require('cors')
const getDockerHost = require('get-docker-host');
const isInDocker = require('is-in-docker');

const routes = require("./../routes");


class Server {
  constructor({ initializer, kafka_producer }) {
    this.checkDocker().then((addr) => {
      if (addr) {
          console.log('Docker host is ' + addr);
          this.init(initializer, kafka_producer);

      } else {
          console.log('Not in Docker');
          this.init(initializer, kafka_producer);

      }
  }).catch((error) => {
      console.log('Could not find Docker host: ' + error);
  });
   
  }

  init(initializer, kafka_producer) {
    this.app = express();
    this.app.use(cors())
    this.app.use(express.static( (os.platform().includes('win') ? __dirname : '') + '/tmp'));
    this.app.use(express.static( (os.platform().includes('win') ? __dirname : '') + '/public'));

    this.app.get("/test", function(req, res) {
      // require('./../../test.js')
      if(os.platform().includes('win'))
        return res.sendFile('/public/index.html')
      return res.sendFile( 'C:\\Users\\J.Haryson\\Desktop\\tcc\\teste\\scraper\\web_scraping_crawller\\public\\index.html');
    });

    this.app.use(express.json());
    this.app.use(bodyParser.urlencoded({ extended: false }));

    if (kafka_producer) this.applyKafkaProducer(this.app, kafka_producer);

    this.routes(this.app);

    if (initializer) this.listen(this.app);
  }

  routes(app) {
    routes(app);
  }

  listen(app) {
    app.listen(process.env.SOURCE_PORT || 3333, () => console.log( `Listening on ${os.networkInterfaces()}:${process.env.SOURCE_PORT || 3333}` ));
  }

  start() {
    this.listen(this.app);
  }

  applyKafkaProducer(app, kafka_producer) {
    app.use((req, res, next) => {
      req.kafka_producer = kafka_producer;
      return next();
    });
  }

  checkDocker = () => {
    return new Promise((resolve, reject) => {
        if (isInDocker()) {
            getDockerHost((error, result) => {
                if (result) {
                    resolve(result);
                } else {
                    reject(error);
                }
            });
        } else {
            resolve(null);
        }
    });
};
}

module.exports = { Server };
