# Stage 1: Build
# Use an official Node.js runtime as a parent image for the build stage
FROM node:latest as build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json (or yarn.lock) to the working directory
COPY package*.json ./

# Install any dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the application for production
RUN npm run build

# Stage 2: Serve
# Use an official nginx image to serve the built application
FROM nginx:alpine
# Copy custom Nginx configuration
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf
# Copy static assets from the build stage to the nginx directory for serving content
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 3000 to the outside world
EXPOSE 3000

# Start nginx and keep the process running
CMD ["nginx", "-g", "daemon off;"]
