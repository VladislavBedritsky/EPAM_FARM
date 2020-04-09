FROM node:12.7-alpine
USER root
RUN npm install -g @angular/cli
COPY . app/
WORKDIR /app
CMD ["ng","serve"]
