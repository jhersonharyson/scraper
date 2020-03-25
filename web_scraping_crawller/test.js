const express = require('express')
const app = express()
const cors = require('cors')
const bodyParser = require("body-parser");
const routes = require("./src/routes");
const getDockerHost = require('get-docker-host');
const isInDocker = require('is-in-docker');

const checkDocker = () => { 
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
}


checkDocker().then((addr) => {
  if (addr) {
      console.log('Docker host is ' + addr);
      global.run_in_docker = true
  } else {
      console.log('Not in Docker');
      global.run_in_docker = false
  }
}).then(()=>{
  run()
})

const run = () => {
  app.use(express.json());
  app.use(bodyParser.urlencoded({ extended: false }));
  app.use(cors())
  app.use(express.static(__dirname + '/tmp'))
  app.use(express.static(__dirname + '/public'))
  routes(app)
  app.listen(3333, ()=>console.log('ok'))
}