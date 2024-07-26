/** @type {import('next').NextConfig} */
const nextConfig = {
    images: {
        remotePatterns: [
            {
                protocol: 'https',
                hostname: 'nextui.org',
                pathname: '/images/**',
            },
            {
                protocol: 'https',
                hostname: 'media-cdn.tripadvisor.com',
                pathname: '/media/vr-splice-j/**',
            },
            {
                protocol: 'https',
                hostname: 'www.redfin.com',
                pathname: '/blog/wp-content/uploads/**',
            },
            {
                protocol: 'https',
                hostname: 'cdn.evhayaliniz.com',
                pathname: '/blog/wp-content/uploads/**',
            },
            {
                protocol: 'https',
                hostname: 'www.peerspace.com',
                pathname: '/resources/wp-content/uploads/**',
            },
            {
                protocol: 'https',
                hostname: 'www.cio.com',
                pathname: '/wp-content/uploads/2023/07/**',
            },
            {
                protocol: 'https',
                hostname: 'cdn.prod.website-files.com',
                pathname: '/620ec747459e13c7cf12a39e/**',
            },
        ],
    },
};

export default nextConfig;
