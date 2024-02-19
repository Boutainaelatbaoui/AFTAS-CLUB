FROM node:18.13.0 as build

WORKDIR /app

COPY package*.json ./

RUN npm install

RUN npm install -g @angular/cli

#COPY . .

RUN ng build --configuration=production

FROM nginx:latest

COPY --from=build app/dist/aftas-frontend /usr/share/nginx/html
COPY node_modules/datatables.net-dt/css/jquery.dataTables.css /usr/share/nginx/html/assets/css/~datatables.net-dt/css/jquery.dataTables.css
COPY node_modules/datatables.net/js/jquery.dataTables.js /usr/share/nginx/html/assets/js/~datatables.net/js/jquery.dataTables.js
EXPOSE 80
