# Use Node base image
FROM node:18

# Set working directory
WORKDIR /app

# Copy and install dependencies
COPY package*.json ./
RUN npm install

# Copy app files
COPY . .

# Expose port and start app
EXPOSE 3000
CMD ["node", "server.js"]
