# Stage 1: Build the Angular app
FROM node:18 AS build
WORKDIR /app

# Install dependencies
COPY package*.json ./
RUN npm install -g @angular/cli
#RUN npm install

# Copy the rest of the application files
COPY . .

# Build the app
RUN npm run build -- --configuration=production --verbose

# Stage 2: Serve the app with Nginx
FROM nginx:alpine
COPY --from=build /app/dist/details /usr/share/nginx/html

# Expose the port the app runs on
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]

# docker build -t containerrepo.azurecr.io/details:v01 .
# docker push containerrepo.azurecr.io/details:v01
