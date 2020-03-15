FROM node:10.15.3-alpine

WORKDIR /var/www

COPY . .

RUN npm install -g yarn 

RUN yarn install

CMD [ "yarn", "start" ]