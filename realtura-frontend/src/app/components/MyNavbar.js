import React from "react";
import { Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button } from "@nextui-org/react";
import Image from 'next/image';
import styles from './page.module.css'

export default function MyNavbar() {
    return (
          <div className={styles.main}>
              <Image src="/logo.jpg" alt="Logo" width={50} height={50} />
              <Link href="/">Home</Link>
              <Link href="../login">Login</Link>
          </div>
    );
}
