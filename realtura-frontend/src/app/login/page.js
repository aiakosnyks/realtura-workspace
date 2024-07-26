// pages/login.js
'use client'
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { toast } from 'react-toastify';
import {useAuth} from "@/app/context/AuthContext";
import {Button, Form, Input, InputNumber, Select} from "antd";
import TextArea from "antd/es/input/TextArea";

const Login = () => {
    const router = useRouter();
    const { login } = useAuth();

    const onFinish = async (values) => {
        console.log('handleLogin')
        try {
            const response = await fetch('http://localhost:8091/api/v1/users/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(values),
            });
            const res = await response.json();
            console.log('resjson:', JSON.stringify(res, null, 2)); // Improved logging

            if (response.ok) {
                console.log('ok');
                if (res.status ==='SUCCESS') {
                    console.log('success');
                    login(res.data.id); // Set userId in context
                    router.push("/listings/mylistings")
                    toast.success('Login successful!');
                } else {
                    toast.error('Login failed: ' + (res.message || 'Unknown error'));
                }
            } else {
                toast.error('Login failed: ' + (res.message || 'Unknown error'));
            }
        } catch (error) {
            toast.error('An error occurred: ' + (error.message || 'Unknown error'));
        }
    }
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
        <div>
            <h1>Login</h1>
            <Form
                name="listing"
                labelCol={{span: 8}}
                wrapperCol={{span: 16}}
                style={{maxWidth: 600}}
                initialValues={{remember: true}}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                autoComplete="off"
            >
                {/* ... other form items ... */}

                <Form.Item
                    label="Email"
                    name="email"
                    rules={[{required: true, message: 'Please input email!'}]}
                >
                    <Input/>
                </Form.Item>
                <Form.Item
                    label="Password"
                    name="password"
                    rules={[{required: true, message: 'Please input password!'}]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item wrapperCol={{offset: 8, span: 16}}>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
};

export default Login;
