// pages/login.js
'use client'
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { toast } from 'react-toastify';
import {useAuth} from "@/app/context/AuthContext";
import {Button, Form, Input, InputNumber, Select} from "antd";
import TextArea from "antd/es/input/TextArea";

const Subscribe = () => {
    const router = useRouter();
    const { userId } = useAuth();

    const onFinish = async (values) => {
        console.log('handleSubscripton')
        try {
            const requestBody = {
                ...values,
                userId: userId
            };
            const response = await fetch('http://localhost:8051/api/v1/subscriptions', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestBody),
            });
            const res = await response.json();
            console.log('resjson:', JSON.stringify(res, null, 2)); // Improved logging

            if (response.ok) {
                console.log('ok');
                if (res.status ==='SUCCESS') {
                    console.log('success');
                    login(res.data.id); // Set userId in context
                    toast.success('Subscription successful!');
                    router.push("/listings/mylistings")
                } else {
                    toast.error('Subscription failed: ' + (res.message || 'Unknown error'));
                }
            } else {
                toast.error('Subscription failed: ' + (res.message || 'Unknown error'));
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
                    label="Amount"
                    name="amount"
                    rules={[{required: true, message: 'Please input amount!'}]}
                >
                    <InputNumber/>
                </Form.Item>
                <Form.Item
                    label="Product Quantity"
                    name="productQuantity"
                    rules={[{required: true, message: 'Please input quantity!'}]}
                >
                    <InputNumber/>
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

export default Subscribe;
