#!/bin/bash

# 🚀 Local CI Pipeline Test Script
# This script simulates GitHub Actions workflow locally

set -e  # Exit on any error

echo "🔧 Starting local CI pipeline test..."
echo "=================================="

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Function to print status
print_status() {
    echo -e "${GREEN}✅ $1${NC}"
}

print_warning() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

print_error() {
    echo -e "${RED}❌ $1${NC}"
}

# Check prerequisites
echo "🔍 Checking prerequisites..."

# Check Java version
java -version
if [ $? -eq 0 ]; then
    print_status "Java is available"
else
    print_error "Java not found"
    exit 1
fi

# Check Gradle wrapper
if [ -f "./gradlew" ]; then
    print_status "Gradle wrapper found"
    chmod +x gradlew
else
    print_error "Gradle wrapper not found"
    exit 1
fi

echo ""
echo "🏗️  Running build tasks..."
echo "========================="

# Step 1: Clean build
echo "1️⃣  Cleaning previous builds..."
if ./gradlew clean --no-daemon; then
    print_status "Clean completed"
else
    print_error "Clean failed"
    exit 1
fi

# Step 2: Full build
echo ""
echo "2️⃣  Running full build..."
if ./gradlew build --no-daemon; then
    print_status "Build completed successfully"
else
    print_error "Build failed"
    exit 1
fi

# Step 3: Run tests
echo ""
echo "3️⃣  Running tests..."
if ./gradlew test --no-daemon; then
    print_status "Tests completed successfully"
else
    print_error "Tests failed"
    exit 1
fi

# Step 4: Build APK
echo ""
echo "4️⃣  Building debug APK..."
if ./gradlew assembleDebug --no-daemon; then
    print_status "APK build completed successfully"
else
    print_error "APK build failed"
    exit 1
fi

# Verify outputs
echo ""
echo "📦 Verifying build outputs..."
echo "============================="

if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
    APK_SIZE=$(du -h app/build/outputs/apk/debug/app-debug.apk | cut -f1)
    print_status "Debug APK created successfully (${APK_SIZE})"
else
    print_error "Debug APK not found"
    exit 1
fi

# Check for test reports
if [ -d "app/build/reports" ]; then
    print_status "Build reports directory exists"
else
    print_warning "Build reports directory not found"
fi

echo ""
echo "🎉 LOCAL CI PIPELINE TEST COMPLETED SUCCESSFULLY! 🎉"
echo "=================================================="
echo ""
echo "📋 Summary:"
echo "   ✅ Build: SUCCESS"
echo "   ✅ Tests: SUCCESS" 
echo "   ✅ APK:   SUCCESS (${APK_SIZE})"
echo ""
echo "" 