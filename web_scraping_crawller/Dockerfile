FROM node:12-alpine

COPY . .

RUN apk add --no-cache \
# RUN apk add chromium \
#       nss \
#       freetype \
#       freetype-dev \
#       harfbuzz \
#       ca-certificates \
#       ttf-freefont \
#       nodejs \
      yarn


# Tell Puppeteer to skip installing Chrome. We'll be using the installed package.
ENV PUPPETEER_SKIP_CHROMIUM_DOWNLOAD true

ENV CHROMIUM_PATH /usr/bin/chromium-browse
# Puppeteer v1.19.0 works with Chromium 77.
# RUN yarn add puppeteer@1.19.0

# # Add user so we don't need --no-sandbox.
# RUN addgroup -S pptruser && adduser -S -g pptruser pptruser \
#     && mkdir -p /home/pptruser/Downloads /app \
#     && chown -R pptruser:pptruser /home/pptruser \
#     && chown -R pptruser:pptruser /app

# # Run everything after as non-privileged user.
# USER pptruser

RUN yarn install

CMD [ "yarn", "start" ]