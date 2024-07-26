// middleware.js or _middleware.js
import { NextResponse } from 'next/server';

export function middleware(request) {
    const response = NextResponse.next();

    response.headers.set('Content-Security-Policy', "script-src 'self' https://apis.google.com");

    return response;
}
