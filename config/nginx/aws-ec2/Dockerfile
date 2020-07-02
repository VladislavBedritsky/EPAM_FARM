FROM nginx:1.19-alpine
USER root
COPY default.conf /etc/nginx/conf.d/
RUN sudo add-apt-repository ppa:certbot/certbot
RUN sudo apt-get update
RUN sudo apt-get install python-certbot-nginx
RUN sudo systemctl reload nginx
RUN sudo ufw allow 'Nginx Full'
RUN sudo ufw delete allow 'Nginx HTTP'
RUN sudo certbot --nginx -d oauth.xfarm.xyz -d properties.xfarm.xyz